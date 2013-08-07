package com.thenaglecode.jsf;

import com.thenaglecode.algorithms.ejb.AlgorithmControllerBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
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
@ViewScoped
public class RandomGeneratorController implements Serializable {

    @EJB
    AlgorithmControllerBean algorithmController;
    long seed;
    long upto;
    String type = "long";
    String value = "0";
    private List<SelectItem> types;

    public RandomGeneratorController() {
        init();
    }

    private void init() {
        types = new ArrayList<>();
        upto = 1000;
        seed = 0;
        types.add(new SelectItem("short"));
        types.add(new SelectItem("int"));
        types.add(new SelectItem("long"));
        types.add(new SelectItem("float"));
        types.add(new SelectItem("double"));
    }

    public List<SelectItem> getTypes() {
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
        if (this.upto != upto) {
            this.upto = upto;
        }
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        if (this.seed != seed) {
            algorithmController.setSeed(seed);
            this.seed = seed;
        }
    }

    public void generateNextNumber() {
        value = algorithmController.getRandomNumber(type, upto);
    }
}
