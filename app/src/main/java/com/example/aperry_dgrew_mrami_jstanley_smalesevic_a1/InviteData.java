//FILENAME      : InviteData.java
//PROJECT       : PROG3150 - Assignment 1
//PROGRAMMER    : Aaron Perry, Daniel Grew, John Stanley, Manthan Rami, Sasha Malesevic
//FIRST VERSION : 2020-02-08

package com.example.aperry_dgrew_mrami_jstanley_smalesevic_a1;

public class InviteData{
    private String name;
    private String email;
    private int picture;

    //CLASS   : InviteData
    //PURPOSE : To hold values used in creating the RecyclerView in the AddFriendActivity. Represents
    //          a party invite recipient, includes name, email and a random vector avatar.
    public InviteData(String name, String email, int picture){
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    //all getters
    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public int getPicture(){
        return picture;
    }
}
