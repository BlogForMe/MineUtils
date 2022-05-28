package com.comm.util.bean;

import java.util.ArrayList;
import java.util.List;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecipeBean implements MultiItemEntity {
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_BOTTOM = 2;
    private int itemType;

    private String drug_name;
    private String frequency;
    private String drug_num;
    private String num;
    private String pharmacy_type;

    public RecipeBean() {
    }

    public RecipeBean(int itemType) {
        this.itemType = itemType;
    }

    public static List<RecipeBean> analysisChild(String receps) throws JSONException {
        List<RecipeBean> list = new ArrayList<>();
//        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray(receps);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            RecipeBean mPatient = new RecipeBean();
            mPatient.setDrug_name(object.getString("drug_name"));
            mPatient.setDrug_num(object.getString("drug_num"));
            mPatient.setFrequency(object.getString("frequency"));
            mPatient.setNum(object.getString("num"));
            mPatient.setDrug_num(object.getString("drug_num"));
            mPatient.setPharmacy_type(object.getString("pharmacy_type"));
            list.add(mPatient);
        }
        return list;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDrug_num() {
        return drug_num;
    }

    public void setDrug_num(String drug_num) {
        this.drug_num = drug_num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPharmacy_type() {
        return pharmacy_type;
    }

    public void setPharmacy_type(String pharmacy_type) {
        this.pharmacy_type = pharmacy_type;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
