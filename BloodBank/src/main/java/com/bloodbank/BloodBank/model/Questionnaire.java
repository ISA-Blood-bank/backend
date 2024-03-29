package com.bloodbank.BloodBank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Questionnaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean question1;
    private boolean question2;
    private boolean question3;
    private boolean question4;
    private boolean question5;
    private boolean question6;
    private boolean question7;
    private boolean question8;
    private boolean question9;
    private boolean question10;
    private boolean question11;
    private boolean question12;
    private boolean question13;
    private boolean question14;
    private boolean question15;
    private boolean question16;
    private boolean question17;
    private boolean question18;
    private boolean question19;
    private boolean question20;
    private boolean question21;
    private boolean question22;
    private boolean question23;
    private boolean question24;
    private boolean question25;
    private boolean question26;
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registred_user_id")
    private RegistredUser registredUser;

    public Questionnaire() {
    }

    public Questionnaire(int id, boolean question1, boolean question2, boolean question3, boolean question4, boolean question5, boolean question6, boolean question7, boolean question8, boolean question9, boolean question10, boolean question11, boolean question12, boolean question13, boolean question14, boolean question15, boolean question16, boolean question17, boolean question18, boolean question19, boolean question20, boolean question21, boolean question22, boolean question23, boolean question24, boolean question25, boolean question26, LocalDateTime date, RegistredUser registredUser) {
        this.id = id;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
        this.question7 = question7;
        this.question8 = question8;
        this.question9 = question9;
        this.question10 = question10;
        this.question11 = question11;
        this.question12 = question12;
        this.question13 = question13;
        this.question14 = question14;
        this.question15 = question15;
        this.question16 = question16;
        this.question17 = question17;
        this.question18 = question18;
        this.question19 = question19;
        this.question20 = question20;
        this.question21 = question21;
        this.question22 = question22;
        this.question23 = question23;
        this.question24 = question24;
        this.question25 = question25;
        this.question26 = question26;
        this.date = date;
        this.registredUser = registredUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isQuestion1() {
        return question1;
    }

    public void setQuestion1(boolean question1) {
        this.question1 = question1;
    }

    public boolean isQuestion2() {
        return question2;
    }

    public void setQuestion2(boolean question2) {
        this.question2 = question2;
    }

    public boolean isQuestion3() {
        return question3;
    }

    public void setQuestion3(boolean question3) {
        this.question3 = question3;
    }

    public boolean isQuestion4() {
        return question4;
    }

    public void setQuestion4(boolean question4) {
        this.question4 = question4;
    }

    public boolean isQuestion5() {
        return question5;
    }

    public void setQuestion5(boolean question5) {
        this.question5 = question5;
    }

    public boolean isQuestion6() {
        return question6;
    }

    public void setQuestion6(boolean question6) {
        this.question6 = question6;
    }

    public boolean isQuestion7() {
        return question7;
    }

    public void setQuestion7(boolean question7) {
        this.question7 = question7;
    }

    public boolean isQuestion8() {
        return question8;
    }

    public void setQuestion8(boolean question8) {
        this.question8 = question8;
    }

    public boolean isQuestion9() {
        return question9;
    }

    public void setQuestion9(boolean question9) {
        this.question9 = question9;
    }

    public boolean isQuestion10() {
        return question10;
    }

    public void setQuestion10(boolean question10) {
        this.question10 = question10;
    }

    public boolean isQuestion11() {
        return question11;
    }

    public void setQuestion11(boolean question11) {
        this.question11 = question11;
    }

    public boolean isQuestion12() {
        return question12;
    }

    public void setQuestion12(boolean question12) {
        this.question12 = question12;
    }

    public boolean isQuestion13() {
        return question13;
    }

    public void setQuestion13(boolean question13) {
        this.question13 = question13;
    }

    public boolean isQuestion14() {
        return question14;
    }

    public void setQuestion14(boolean question14) {
        this.question14 = question14;
    }

    public boolean isQuestion15() {
        return question15;
    }

    public void setQuestion15(boolean question15) {
        this.question15 = question15;
    }

    public boolean isQuestion16() {
        return question16;
    }

    public void setQuestion16(boolean question16) {
        this.question16 = question16;
    }

    public boolean isQuestion17() {
        return question17;
    }

    public void setQuestion17(boolean question17) {
        this.question17 = question17;
    }

    public boolean isQuestion18() {
        return question18;
    }

    public void setQuestion18(boolean question18) {
        this.question18 = question18;
    }

    public boolean isQuestion19() {
        return question19;
    }

    public void setQuestion19(boolean question19) {
        this.question19 = question19;
    }

    public boolean isQuestion20() {
        return question20;
    }

    public void setQuestion20(boolean question20) {
        this.question20 = question20;
    }

    public boolean isQuestion21() {
        return question21;
    }

    public void setQuestion21(boolean question21) {
        this.question21 = question21;
    }

    public boolean isQuestion22() {
        return question22;
    }

    public void setQuestion22(boolean question22) {
        this.question22 = question22;
    }

    public boolean isQuestion23() {
        return question23;
    }

    public void setQuestion23(boolean question23) {
        this.question23 = question23;
    }

    public boolean isQuestion24() {
        return question24;
    }

    public void setQuestion24(boolean question24) {
        this.question24 = question24;
    }

    public boolean isQuestion25() {
        return question25;
    }

    public void setQuestion25(boolean question25) {
        this.question25 = question25;
    }

    public boolean isQuestion26() {
        return question26;
    }

    public void setQuestion26(boolean question26) {
        this.question26 = question26;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public RegistredUser getRegistredUser() {
        return registredUser;
    }

    public void setRegistredUser(RegistredUser registredUser) {
        this.registredUser = registredUser;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", question1=" + question1 +
                ", question2=" + question2 +
                ", question3=" + question3 +
                ", question4=" + question4 +
                ", question5=" + question5 +
                ", question6=" + question6 +
                ", question7=" + question7 +
                ", question8=" + question8 +
                ", question9=" + question9 +
                ", question10=" + question10 +
                ", question11=" + question11 +
                ", question12=" + question12 +
                ", question13=" + question13 +
                ", question14=" + question14 +
                ", question15=" + question15 +
                ", question16=" + question16 +
                ", question17=" + question17 +
                ", question18=" + question18 +
                ", question19=" + question19 +
                ", question20=" + question20 +
                ", question21=" + question21 +
                ", question22=" + question22 +
                ", question23=" + question23 +
                ", question24=" + question24 +
                ", question25=" + question25 +
                ", question26=" + question26 +
                ", date=" + date +
                ", registredUser=" + registredUser +
                '}';
    }
}
