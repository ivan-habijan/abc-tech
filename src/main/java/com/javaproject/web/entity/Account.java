package com.javaproject.web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.text.ParseException;
import java.sql.Date;
import java.text.SimpleDateFormat;


@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Racun_id")
    private int racunId;
    @Column(name = "iban", length = 20, nullable = false)
    @Pattern(regexp = "^[A-Z]{2}[0-9]{10}$", message = "IBAN mora biti u formatu HR1234567890")
    private String iban;
    @Column(name = "tip_racuna")
    @Range(min = 1, max = 2, message = "Tip racuna mora biti 1 ili 2")
    private int tip;
    @Column(length = 3, name = "valuta", nullable = false)
    @Pattern(regexp = "^[A-Z]{2,3}$", message = "Samo slova dopustena")
    @Size(min = 2, max = 3, message = "Valuta mora biti 2 ili 3 charactera")
    private String valuta;

    @Column(name = "klijent_id", nullable = false)
    @Min(value = 1, message = "ID mora biti veci od 0")
    private int klijentId;

    @Column(name = "datum_otvaranja", nullable = false)
    private Date datumOtvaranja;

    @Column(name = "datum_zatvaranja", nullable = true)
    private Date datumZatvaranja;

    public Account() {
    }

    public Account(int racunId, String iban, int tip, String valuta, int klijentId, String datumOtvaranja) throws ParseException {
        this.iban = iban;
        this.tip = tip;
        this.valuta = valuta;
        this.klijentId = klijentId;
        this.datumOtvaranja = stringToDate(datumOtvaranja);
    }

    public Account(int racunId, String iban, int tip, String valuta, int klijentId, String datumOtvaranja, String datumZatvaranja) throws ParseException {
        this.iban = iban;
        this.tip = tip;
        this.valuta = valuta;
        this.klijentId = klijentId;
        this.datumOtvaranja = stringToDate(datumOtvaranja);
        this.datumZatvaranja = stringToDate(datumZatvaranja);
    }

    public int getRacunId() {
        return racunId;
    }

    public void setRacunId(int racunId) {
        this.racunId = racunId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public int getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(int klijentId) {
        this.klijentId = klijentId;
    }

    public Date getDatumOtvaranja() {
        return datumOtvaranja;
    }

    public void setDatumOtvaranja(Date datumOtvaranja) {
        this.datumOtvaranja = datumOtvaranja;
    }

    public Date getDatumZatvaranja() {
        return datumZatvaranja;
    }

    public void setDatumZatvaranja(Date datumZatvaranja) {
        this.datumZatvaranja = datumZatvaranja;
    }

    public Date stringToDate(String inputDate) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd"); // New Pattern
        java.util.Date date = sdf1.parse(inputDate); // Returns a Date format object with the pattern
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        return sqlStartDate;
    }

}
