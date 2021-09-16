package com.codecool.shop.service;


import java.io.Serializable;
import java.util.UUID;

public class UserOrder implements Serializable{
        private int orderId;
        private UUID userId;
        private String username;
        private String email;
        private String phoneNumber;
        private String address;
        private String city;
        private String zip;
        private String cName;
        private String cNum;
        private String expDate;
        private String cvv;

        public UserOrder(int orderId, UUID userId, String username, String email, String phoneNumber, String address, String city, String zip) {
            this.orderId = orderId;
            this.userId = userId;
            this.username = username;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.city = city;
            this.zip = zip;
        }

        public void setPaymentData(String cName, String cNum, String expDate, String cvv) {
            this.cName = cName;
            this.cNum = cNum;
            this.expDate = expDate;
            this.cvv = cvv;
        }

        public UUID getUserId() {
            return this.userId;
        }

        public int getOrderId() {
            return this.orderId;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public String getCity() {
            return city;
        }

        public String getZip() {
            return zip;
        }

        public String getcName() {
            return cName;
        }

        public String getcNum() {
            return cNum;
        }

        public String getExpDate() {
            return expDate;
        }

        public String getCvv() {
            return cvv;
        }
}
