package com.imobiliaria.test;

import lombok.Data;

@Data
public class TestLombok {
    private String nome;
    private int idade;

    public static void main(String[] args) {
        TestLombok test = new TestLombok();
        test.setNome("João");
        test.setIdade(30);

        System.out.println("Nome: " + test.getNome());
        System.out.println("Idade: " + test.getIdade());
    }
}
