package com.clz619.sinno.mobile.models;

import java.util.HashMap;
import java.util.Map;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Index;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Indexes;
import com.google.code.morphia.annotations.Transient;

import play.modules.morphia.Model;

/**
 * 
 * @project sinno
 * @title MobileSegment.java
 * @author lizhong.chen
 * @data 2014年11月23日下午6:53:18
 * @description 电话网段 模型
 * @tag
 * @version V1.0
 */
@Entity(value = "mobile.segment", noClassnameStored = true)
public class MobileSegment extends Model {

    // 北京=1,天津=2,河北省=3,山西省=4,内蒙古自治区=5,辽宁省=6,吉林省=7,黑龙江省=8,上海=9,江苏省=10,浙江省=11,安徽省=
    // 12,福建省=13,江西省=14,山东省=15,河南省=16,湖北省=17,湖南省=18,
    // 广东省=19,广西壮族自治区=20,海南省=21,重庆=
    // 22,四川省=23,贵州省=24,云南省=25,西藏自治区26,陕西省=27,甘肃省=28,
    // 青海省=29,宁夏回族自治区=30,新疆维吾尔自治区=31,台湾省=32,香港特别行政区=33,澳门特别行政区=34,海外=35

    @Transient
    private static Long[][] provinceType = { { 110000l, 1l }, { 120000l, 2l }, { 130000l, 3l }, { 140000l, 4l },
            { 150000l, 5l }, { 210000l, 6l }, { 220000l, 7l }, { 230000l, 8l }, { 310000l, 9l }, { 320000l, 10l }

            , { 330000l, 11l }, { 340000l, 12l }, { 350000l, 13l }, { 360000l, 14l }, { 370000l, 15l }

            , { 410000l, 16l }, { 420000l, 17l }, { 430000l, 18l }, { 440000l, 19l }, { 450000l, 20l }

            , { 460000l, 21l }, { 500000l, 22l }, { 510000l, 23l }, { 520000l, 24l }, { 530000l, 25l }

            , { 540000l, 26l }, { 610000l, 27l }, { 620000l, 28l }, { 630000l, 29l }, { 640000l, 30l }

            , { 650000l, 31l }, { 710000l, 32l }, { 810000l, 33l }, { 820000l, 34l }, { 990000l, 35l }

    };

    /**
     * 省份类型
     */
    @Transient
    private static final Map<String, Long> PROVINCE_TYPE = new HashMap<String, Long>();
    static {
        PROVINCE_TYPE.put("北京", 110000l);
        PROVINCE_TYPE.put("天津", 120000l);
        PROVINCE_TYPE.put("河北", 130000l);
        PROVINCE_TYPE.put("山西", 140000l);
        PROVINCE_TYPE.put("内蒙古", 150000l);

        PROVINCE_TYPE.put("辽宁", 210000l);
        PROVINCE_TYPE.put("吉林", 220000l);
        PROVINCE_TYPE.put("黑龙江", 230000l);
        PROVINCE_TYPE.put("上海", 310000l);
        PROVINCE_TYPE.put("江苏", 320000l);

        PROVINCE_TYPE.put("浙江", 330000l);
        PROVINCE_TYPE.put("安徽", 340000l);
        PROVINCE_TYPE.put("福建", 350000l);
        PROVINCE_TYPE.put("江西", 360000l);
        PROVINCE_TYPE.put("山东", 370000l);

        PROVINCE_TYPE.put("河南", 410000l);
        PROVINCE_TYPE.put("湖北", 420000l);
        PROVINCE_TYPE.put("湖南", 430000l);
        PROVINCE_TYPE.put("广东", 440000l);
        PROVINCE_TYPE.put("广西", 450000l);

        PROVINCE_TYPE.put("海南", 460000l);
        PROVINCE_TYPE.put("重庆", 500000l);
        PROVINCE_TYPE.put("四川", 510000l);
        PROVINCE_TYPE.put("贵州", 520000l);
        PROVINCE_TYPE.put("云南", 530000l);

        PROVINCE_TYPE.put("西藏", 540000l);
        PROVINCE_TYPE.put("陕西", 610000l);
        PROVINCE_TYPE.put("甘肃", 620000l);
        PROVINCE_TYPE.put("青海", 630000l);
        PROVINCE_TYPE.put("宁夏", 640000l);

        PROVINCE_TYPE.put("新疆", 650000l);
        PROVINCE_TYPE.put("台湾", 710000l);
        PROVINCE_TYPE.put("香港", 810000l);
        PROVINCE_TYPE.put("澳门", 820000l);
        PROVINCE_TYPE.put("海外", 990000l);

    }

    @Indexed(name = "idx_mobile_part")
    private String mobilePart;
    private String province;
    private String city;
    private String mobileType;
    private int type;
    private String areaCode;
    private String postCode;
    private long provinceCode;

    public String getMobilePart() {
        return mobilePart;
    }

    public void setMobilePart(String mobilePart) {
        this.mobilePart = mobilePart;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;

        Long ptype = PROVINCE_TYPE.get(province);
        if (ptype != null) {
            setProvinceCode(ptype);
        } else {
            setProvinceCode(330000l);

        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobileType() {
        return mobileType;
    }

    public void setMobileType(String mobileType) {
        this.mobileType = mobileType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String acreCode) {
        this.areaCode = acreCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(long provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Override
    public String toString() {
        return "{'mobilePart':'" + mobilePart + "','province':'" + province + "','mobileType':'" + mobileType + "'}";
    }

    /**
     * 
     * @project sinno
     * @title MobileSegment.java
     * @author lizhong.chen
     * @data 2014年11月23日下午6:52:44
     * @description 号码网段类型
     * @tag
     * @version V1.0
     */
    public static enum Type {
        /**
         * 移动
         */
        YD(1, "移动", "yd"),
        /**
         * 联通
         */
        LT(2, "联通", "lt"),
        /**
         * 电信
         */
        DX(4, "电信", "dx");
        private Type(int index, String valueCn, String valueEn) {
            this.index = index;
            this.valueCn = valueCn;
            this.valueEn = valueEn;
        }

        private int index;
        private String valueCn;
        private String valueEn;

        public int getIndex() {
            return index;
        }

        public String getValueCn() {
            return valueCn;
        }

        public String getValueEn() {
            return valueEn;
        }

        public int getIndexForMobileType(String mobileType) {
            Type[] types = values();
            for (Type type : types) {
                if (mobileType.contains(type.getValueCn())) {
                    return type.getIndex();
                }
            }
            return YD.getIndex();
        }

    }

}
