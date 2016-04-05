package com.example.brotherj.uidesign.bean;

/**
 * Created by user on 5/4/2016.
 */
public class Food {

    String id;
    String name;
    String type;
    String price;
    String image;
    String Restaurantid;

    public Food(String id,String name,String type,String price,String image,String Restaurantid){
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.image = image;
        this.Restaurantid = Restaurantid;
    }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getRestaurantid() {
            return Restaurantid;
        }

        public void setRestaurantid(String Restaurantid) {
            this.Restaurantid = Restaurantid;
        }

}
