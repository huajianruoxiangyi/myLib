package com.uerbeautybusiness.uersbeauty.module.bean;

import java.util.List;

/**
 * @创建者 tianzeyu
 * @创建时间 2019/6/17 15:56
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 2019/6/17$
 * @更新描述 ${TODO}
 */

public class SportHomeBean {


    /**
     * status : 2
     * message : ok
     * data : {"banner":[{"id":253,"title":"奥运会报名","redirect_url":"http://mtest.wasaisports.com/regSport/?","logo":"attachments/2019/07/15641050333d556a1879942449.jpg","start_time":1564070400,"end_time":1567094400,"url_share":"http://mtest.wasaisports.com/regSportShare/?","logo_share":"attachments/2019/07/156410503376493d0ddfc0d130.jpg","title_share":"奥运会报名","content_share":"奥运会报名","rule":"","type":0}],"sportList":[{"id":1,"title":"跑步直播间","status":"1","type":"1","background_image":"attachments/2019/06/156082945421cfab45fdfcb0ed.png","totalPeople":0,"sports_type":0},{"id":2,"title":"竞技单车","status":"0","type":"2","background_image":"attachments/2019/06/1560829429e5efad40ca2ad272.png","totalPeople":0,"sports_type":2},{"id":3,"title":"跑步机","status":"0","type":"3","background_image":"attachments/2019/06/15608294155a907aab0f830176.png","totalPeople":0,"sports_type":1},{"id":4,"title":"划船机","status":"0","type":"4","background_image":"attachments/2019/06/156082939676ff1b4ad173fc26.png","totalPeople":0,"sports_type":3},{"id":5,"title":"射击","status":"1","type":"5","background_image":"attachments/2019/06/15608293748e25b6969cdf9a35.png","totalPeople":22,"sports_type":4},{"id":6,"title":"飞镖","status":"1","type":"6","background_image":"attachments/2019/06/15608293535e1509994173678e.png","totalPeople":7,"sports_type":5}]}
     */

    private String status;
    private String message;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<BannerBean> banner;
        private List<SportListBean> sportList;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<SportListBean> getSportList() {
            return sportList;
        }

        public void setSportList(List<SportListBean> sportList) {
            this.sportList = sportList;
        }

        public static class BannerBean {
            /**
             * id : 253
             * title : 奥运会报名
             * redirect_url : http://mtest.wasaisports.com/regSport/?
             * logo : attachments/2019/07/15641050333d556a1879942449.jpg
             * start_time : 1564070400
             * end_time : 1567094400
             * url_share : http://mtest.wasaisports.com/regSportShare/?
             * logo_share : attachments/2019/07/156410503376493d0ddfc0d130.jpg
             * title_share : 奥运会报名
             * content_share : 奥运会报名
             * rule :
             * type : 0
             */

            private String id;
            private String title;
            private String redirect_url;
            private String logo;
            private int start_time;
            private int end_time;
            private String url_share;
            private String logo_share;
            private String title_share;
            private String content_share;
            private String rule;
            private int type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getRedirect_url() {
                return redirect_url;
            }

            public void setRedirect_url(String redirect_url) {
                this.redirect_url = redirect_url;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public int getStart_time() {
                return start_time;
            }

            public void setStart_time(int start_time) {
                this.start_time = start_time;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public String getUrl_share() {
                return url_share;
            }

            public void setUrl_share(String url_share) {
                this.url_share = url_share;
            }

            public String getLogo_share() {
                return logo_share;
            }

            public void setLogo_share(String logo_share) {
                this.logo_share = logo_share;
            }

            public String getTitle_share() {
                return title_share;
            }

            public void setTitle_share(String title_share) {
                this.title_share = title_share;
            }

            public String getContent_share() {
                return content_share;
            }

            public void setContent_share(String content_share) {
                this.content_share = content_share;
            }

            public String getRule() {
                return rule;
            }

            public void setRule(String rule) {
                this.rule = rule;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class SportListBean {
            /**
             * id : 1
             * title : 跑步直播间
             * status : 1
             * type : 1
             * background_image : attachments/2019/06/156082945421cfab45fdfcb0ed.png
             * totalPeople : 0
             * sports_type : 0
             */

            private int id;
            private String title;
            private String status;
            private String type;
            private String background_image;
            private int totalPeople;
            private int sports_type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getBackground_image() {
                return background_image;
            }

            public void setBackground_image(String background_image) {
                this.background_image = background_image;
            }

            public int getTotalPeople() {
                return totalPeople;
            }

            public void setTotalPeople(int totalPeople) {
                this.totalPeople = totalPeople;
            }

            public int getSports_type() {
                return sports_type;
            }

            public void setSports_type(int sports_type) {
                this.sports_type = sports_type;
            }
        }
    }
}
