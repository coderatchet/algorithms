package com.thenaglecode.jsf;

import com.thenaglecode.algorithms.ejb.AlgorithmControllerBean;
import javax.faces.model.SelectItem;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/1/13
 * Time: 4:01 PM
 */

@ManagedBean(name = "random")
@SessionScoped
public class RandomGeneratorController implements Serializable {

    private List<SelectItem> types;

    @EJB
    AlgorithmControllerBean algorithmController;

    long seed = 0;
    long upto = 1000;
    String type = "long";
    String value = "0";

    public RandomGeneratorController(){
        init();
    }

    private void init() {
        types = new ArrayList<>();
        types.add(new SelectItem("short"));
        types.add(new SelectItem("int"));
        types.add(new SelectItem("long"));
        types.add(new SelectItem("float"));
        types.add(new SelectItem("double"));
    }

    public List<SelectItem> getTypes(){
        return types;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public long getUpto() {
        return upto;
    }

    public void setUpto(long upto) {
        this.upto = upto;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed() {
        algorithmController.setSeed(seed);
    }

    public String generateNextNumber() {
        value = algorithmController.getRandomNumber(type, upto);
        return value;
    }
}
