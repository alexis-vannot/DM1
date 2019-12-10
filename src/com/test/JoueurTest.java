package com.test;

import com.jeu_pion.Joueur;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JoueurTest {

    static Joueur j;

    @Before
    public void setUp() throws Exception {
        j = new Joueur("Alexis", "X");
    }

    @Test
    public void setPrenom() {
        j.setPrenom("Paul");
        assertTrue(j.getPrenom() == "Paul");
    }

    @Test
    public void setCaractere() {
        j.setCaractere("R");
        assertTrue(j.getCaractere() == "R");
    }

    @Test
    public void getPrenom() {
        j.setPrenom("Patrick");
        assertTrue(j.getPrenom() == "Patrick");
    }

    @Test
    public void getCaractere() {
        j.setCaractere("J");
        assertTrue(j.getCaractere() == "J");
    }
}