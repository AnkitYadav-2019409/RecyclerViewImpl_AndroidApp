package com.example.recyclerviewcontactapp;

class Contact {
    private final int ID;
    private final String Name;
    private final String Phone;
    private final String Email;



    public Contact(int ID, String name, String phone, String email) {
        this.ID = ID;
        this.Name = name;
        this.Phone = phone;
        this.Email = email;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }
    public String getEmail() {
        return Email;
    }
}
