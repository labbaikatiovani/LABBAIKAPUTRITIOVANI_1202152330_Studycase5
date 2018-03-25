package com.example.android.labbaikaputritiovani_1202152330_studycase5;

public class AddData {
    //deklarasi variable
    String todo, desc, prior;

    //konstruktor
    public AddData(String todo, String desc, String prior){
        this.todo=todo;
        this.desc=desc;
        this.prior=prior;
    }

    //method setter and getter untuk to do list, description and priority
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
