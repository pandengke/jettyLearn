package com.studio.service.xls.pojo;

import com.google.gson.Gson;

import java.io.File;

/**
 * Created by Administrator on 2016/3/24.
 */
public class DocInfo {
    private String qua_zon_hao;
    private String mu_lu_hao;
    private String fen_lei_hao;
    private String nia_du;
    private String bao_gua_qua_xia;
    private String an_jua_hao;
    private String zon_di_hao;


    public DocInfo(String qua_zon_hao, String mu_lu_hao, String fen_lei_hao, String nia_du, String bao_gua_qua_xia, String an_jua_hao, String zon_di_hao) {
        this.qua_zon_hao = qua_zon_hao;
        this.mu_lu_hao = mu_lu_hao;
        this.fen_lei_hao = fen_lei_hao;
        this.nia_du = nia_du;
        this.bao_gua_qua_xia = bao_gua_qua_xia;
        this.an_jua_hao = an_jua_hao;
        this.zon_di_hao = zon_di_hao;
    }


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public String getRelativeFolder() {
        String folder = "";
        folder += "\\" + qua_zon_hao;
        folder += "\\" + mu_lu_hao;
        folder += "\\" + fen_lei_hao;
        folder += "\\" + nia_du;
        folder += "\\" + an_jua_hao;
        return folder + "\\";
    }

    public File[] getRelativeFiles(String parentFolder) {
        String folder = parentFolder + getRelativeFolder();
        File file = new File(folder);
        if (file.isDirectory()) {
            return file.listFiles();
        } else {
            throw new IllegalArgumentException("can not find this folder " + folder);
        }
    }
}
