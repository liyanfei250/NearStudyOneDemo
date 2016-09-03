package com.ddm.cityselector.CitySelect.Model;

import java.util.List;

/**
 * Created by DPro on 5/1/16.
 * at 18:55
 * at 6:55 PM
 */
public class CityBean {

    /**
     * province : 北京
     * city : [{"cityName":"北京","code":"101010100"},{"cityName":"朝阳","code":"101010300"},{"cityName":"顺义","code":"101010400"},{"cityName":"怀柔","code":"101010500"},{"cityName":"通州","code":"101010600"},{"cityName":"昌平","code":"101010700"},{"cityName":"延庆","code":"101010800"},{"cityName":"丰台","code":"101010900"},{"cityName":"石景山","code":"101011000"},{"cityName":"大兴","code":"101011100"},{"cityName":"房山","code":"101011200"},{"cityName":"密云","code":"101011300"},{"cityName":"门头沟","code":"101011400"},{"cityName":"平谷","code":"101011500"},{"cityName":"八达岭","code":"101011600"},{"cityName":"佛爷顶","code":"101011700"},{"cityName":"汤河口","code":"101011800"},{"cityName":"密云上甸子","code":"101011900"},{"cityName":"斋堂","code":"101012000"},{"cityName":"霞云岭","code":"101012100"},{"cityName":"北京城区","code":"101012200"},{"cityName":"海淀","code":"101010200"}]
     */

    private List<CityCodeBean> cityCode;

    public List<CityCodeBean> getCityCode() {
        return cityCode;
    }

    public void setCityCode(List<CityCodeBean> cityCode) {
        this.cityCode = cityCode;
    }

    public static class CityCodeBean {
        private String province;
        /**
         * cityName : 北京
         * code : 101010100
         */

        private List<City> city;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public List<City> getCity() {
            return city;
        }

        public void setCity(List<City> city) {
            this.city = city;
        }

        public static class City {
            private String cityName;
            private String code;

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
