package org.cnc_csv_viewer.data;

import java.util.ArrayList;

/**
 * @author Bruno Lopes
 */
public class Beam {

    private int length;
    private String label;
    // 
    private ArrayList<Integer> DIMPLE;
    private ArrayList<Integer> LIP_CUT;
    private ArrayList<Integer> SWAGE;
    private ArrayList<Integer> END_TRUSS;
    private ArrayList<Integer> NOTCH;
    private ArrayList<Integer> serviceHoles;
    private ArrayList<Integer> webHoles;

    public Beam(String label) {
        this.length = 0;
        this.label = label;
        this.LIP_CUT = new ArrayList<>();
        this.SWAGE = new ArrayList<>();
        this.END_TRUSS = new ArrayList<>();
        this.NOTCH = new ArrayList<>();
        this.serviceHoles = new ArrayList<>();
        this.webHoles = new ArrayList<>();
        this.DIMPLE = new ArrayList<>();
    }

    public void addLIP_CUT(int comp) {
        getLIP_CUT().add(comp);
    }

    public void addSWAGE(int comp) {
        getSWAGE().add(comp);
    }

    public void addEND_TRUSS(int comp) {
        getEND_TRUSS().add(comp);
    }

    public void addNOTCH(int comp) {
        getNOTCH().add(comp);
    }

    public void addDIMPLE(int c) {
        if (getDIMPLE() == null) {
            setDIMPLE(new ArrayList<Integer>());
        }
        getDIMPLE().add(new Integer(c));

    }

    public void addServiceHole(Integer c) {
        if (getServiceHoles() == null) {
            setServiceHoles(new ArrayList<Integer>());
        }

        for (Integer integer : getServiceHoles()) {
            if (integer == c) {
                return;
            }
        }

        getServiceHoles().add(c);
    }

    public void addWebHole(Integer c) {
        if (getWebHoles() == null) {
            setWebHoles(new ArrayList<Integer>());
        }
        for (Integer integer : getWebHoles()) {
            if (integer == c) {
                return;
            }
        }
        getWebHoles().add(c);
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the LIP_CUT
     */
    public ArrayList<Integer> getLIP_CUT() {
        return LIP_CUT;
    }

    /**
     * @param LIP_CUT the LIP_CUT to set
     */
    public void setLIP_CUT(ArrayList<Integer> LIP_CUT) {
        this.LIP_CUT = LIP_CUT;
    }

    /**
     * @return the SWAGE
     */
    public ArrayList<Integer> getSWAGE() {
        return SWAGE;
    }

    /**
     * @param SWAGE the SWAGE to set
     */
    public void setSWAGE(ArrayList<Integer> SWAGE) {
        this.SWAGE = SWAGE;
    }

    /**
     * @return the END_TRUSS
     */
    public ArrayList<Integer> getEND_TRUSS() {
        return END_TRUSS;
    }

    /**
     * @param END_TRUSS the END_TRUSS to set
     */
    public void setEND_TRUSS(ArrayList<Integer> END_TRUSS) {
        this.END_TRUSS = END_TRUSS;
    }

    /**
     * @return the NOTCH
     */
    public ArrayList<Integer> getNOTCH() {
        return NOTCH;
    }

    /**
     * @param NOTCH the NOTCH to set
     */
    public void setNOTCH(ArrayList<Integer> NOTCH) {
        this.NOTCH = NOTCH;
    }

    /**
     * @return the serviceHoles
     */
    public ArrayList<Integer> getServiceHoles() {
        return serviceHoles;
    }

    /**
     * @param serviceHoles the serviceHoles to set
     */
    public void setServiceHoles(ArrayList<Integer> serviceHoles) {
        this.serviceHoles = serviceHoles;
    }

    /**
     * @return the webHoles
     */
    public ArrayList<Integer> getWebHoles() {
        return webHoles;
    }

    /**
     * @param webHoles the webHoles to set
     */
    public void setWebHoles(ArrayList<Integer> webHoles) {
        this.webHoles = webHoles;
    }

    /**
     * @return the DIMPLE
     */
    public ArrayList<Integer> getDIMPLE() {
        return DIMPLE;
    }

    /**
     * @param parafusos the DIMPLE to set
     */
    public void setDIMPLE(ArrayList<Integer> parafusos) {
        this.DIMPLE = parafusos;
    }
}
