package com.thenaglecode;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Macindows
 * Date: 9/23/13
 * Time: 8:03 AM
 */
public class ServiceMuncher implements Muncher {

    public static Map<String, ServiceInfo> services = new HashMap<>();
    private InputStream in;

    public ServiceMuncher(@NotNull InputStream in){
        this.in = in;
    }

    /**
     * Munches a sc queryex command to store all the services
     * @throws IOException
     */
    public void munch() throws IOException {
        if(in == null) throw new NullPointerException("in was null");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line;
            ServiceInfo currentInfo = null;
            boolean previousWasState = false;
            while ((line = br.readLine()) != null) {
                if("\n".equals(line) || StringUtils.isBlank(line))continue;
                if(line.trim().startsWith("(") && previousWasState) {
                    Set<Object> attributes = new TreeSet<>();
                    line = line.trim();
                    line = line.substring(1, line.length() - 2); // get rid of parenthases
                    String[] split = StringUtils.split(line, ",");
                    for(String str : split){
                        attributes.add(str.trim());
                    }
                    if(currentInfo == null) throw new NullPointerException();
                    currentInfo.getState().setAttributes(attributes);
                    continue;
                }
                previousWasState = false; //we've passed the condition so don't go into the state thingy
                if(!line.contains(":") || line.startsWith("C")) continue;
                String[] split = StringUtils.split(line, ":");
                if(split.length != 2){
                    break; // error in input
                }
                String key = split[0].trim();
                String value = split[1].trim();
                switch (key){
                    case "SERVICE_NAME" : {
                        String serviceName = split[1].trim();
                        currentInfo = services.get(serviceName);
                        if(currentInfo == null){
                            currentInfo = new ServiceInfo();
                            currentInfo.setServiceName(serviceName);
                            services.put(serviceName, currentInfo);
                        }
                        break;
                    }
                    case "DISPLAY_NAME" : {
                        if (currentInfo != null) {
                            currentInfo.setDisplayName(value);
                        }
                        break;
                    }
                    case "TYPE" : {
                        String[] valueSplit = StringUtils.split(value, " ");
                        TypeInfo type = new TypeInfo(Integer.valueOf(valueSplit[0].trim()), valueSplit[1].trim(), new TreeSet<>());
                        if (currentInfo != null) {
                            currentInfo.setType(type);
                        }
                        break;
                    }
                    case "STATE" : {
                        previousWasState = true;
                        String[] valueSplit = StringUtils.split(value, " ");
                        TypeInfo state = new TypeInfo(Integer.valueOf(valueSplit[0].trim()), valueSplit[1].trim(), new TreeSet<>());
                        if (currentInfo != null) {
                            currentInfo.setState(state);
                        }
                        break;
                    }
                    case "PID" : {
                        if (currentInfo != null) {
                            currentInfo.setPid(Long.valueOf(value.trim()));
                        }
                        break;
                    }
                    default: {

                    }
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            munch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
