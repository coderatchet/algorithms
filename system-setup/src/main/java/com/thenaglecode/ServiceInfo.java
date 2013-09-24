package com.thenaglecode;

import java.util.HashMap;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Macindows
 * Date: 9/23/13
 * Time: 7:28 AM
 */
public class ServiceInfo extends HashMap<String, String> {
    String serviceName;
    String displayName;
    TypeInfo type;
    TypeInfo state;
    TypeInfo win32ExitCode;
    TypeInfo serviceExitCode;
    long checkpoint;
    long waitHint;
    long pid;
    Set<String> flags;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public TypeInfo getType() {
        return type;
    }

    public void setType(TypeInfo type) {
        this.type = type;
    }

    public TypeInfo getState() {
        return state;
    }

    public void setState(TypeInfo state) {
        this.state = state;
    }

    public TypeInfo getWin32ExitCode() {
        return win32ExitCode;
    }

    public void setWin32ExitCode(TypeInfo win32ExitCode) {
        this.win32ExitCode = win32ExitCode;
    }

    public TypeInfo getServiceExitCode() {
        return serviceExitCode;
    }

    public void setServiceExitCode(TypeInfo serviceExitCode) {
        this.serviceExitCode = serviceExitCode;
    }

    public long getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(long checkpoint) {
        this.checkpoint = checkpoint;
    }

    public long getWaitHint() {
        return waitHint;
    }

    public void setWaitHint(long waitHint) {
        this.waitHint = waitHint;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public Set<String> getFlags() {
        return flags;
    }

    public void setFlags(Set<String> flags) {
        this.flags = flags;
    }

}
