/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnc_csv_viewer.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JTextArea;
import org.cnc_csv_viewer.data.Beam;

/**
 *
 * @author Bruno
 */
public class CSVReader {

    private int maxLength;

    public int getMaxLength() {
        return maxLength;
    }

    public ArrayList<Beam> readFile(String path, JTextArea txtArea) throws Exception {
        maxLength = 0;
        ArrayList<Beam> lista = new ArrayList<>();
        txtArea.setText("");
        FileReader input = new FileReader(path);
        BufferedReader buff = new BufferedReader(input);
        String line;

        while ((line = buff.readLine()) != null) {
            txtArea.append(line + "\n");
            String[] array1 = line.split(",");
            if (array1[0].equalsIgnoreCase("COMPONENT")) {
                Beam b = new Beam(array1[1]);
                int comp = (int) Double.parseDouble(array1[4]);
                b.setLength(comp);
                if (maxLength < comp) {
                    maxLength = comp;
                }

                for (int i = 5; i < array1.length; i = i + 2) {
                    if (array1[i].equalsIgnoreCase("DIMPLE")) {
                        comp = (int) Double.parseDouble(array1[i + 1]);
                        b.addDIMPLE(comp);
                    } else if (array1[i].equalsIgnoreCase("LIP_CUT")) {
                        comp = (int) Double.parseDouble(array1[i + 1]);
                        b.addLIP_CUT(comp);
                    } else if (array1[i].equalsIgnoreCase("SWAGE")) {
                        comp = (int) Double.parseDouble(array1[i + 1]);
                        b.addSWAGE(comp);
                    } else if (array1[i].equalsIgnoreCase("END_TRUSS")) {
                        comp = (int) Double.parseDouble(array1[i + 1]);
                        b.addEND_TRUSS(comp);
                    } else if (array1[i].equalsIgnoreCase("NOTCH")) {
                        comp = (int) Double.parseDouble(array1[i + 1]);
                        b.addNOTCH(comp);
                    } else if (array1[i].equalsIgnoreCase("SERVICE_HOLE")) {
                        comp = (int) Double.parseDouble(array1[i + 1]);
                        b.addServiceHole(comp);
                    } else if (array1[i].equalsIgnoreCase("WEB")) {
                        comp = (int) Double.parseDouble(array1[i + 1]);
                        b.addWebHole(comp);
                    }
                }
                lista.add(b);
            }
        }

        return lista;
// COMPONENT,Quadrado_H1,LABEL_NRM,1,1200.0,DIMPLE,22.0,DIMPLE,530.0,DIMPLE,600.0,DIMPLE,670.0,DIMPLE,1178.0,LIP_CUT,22.0,LIP_CUT,530.0,LIP_CUT,600.0,LIP_CUT,670.0,LIP_CUT,1178.0

    }
}
