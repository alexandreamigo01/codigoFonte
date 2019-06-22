/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.co.a2x.utilitarios;

/**
 *
 * @author a2x
 */


public class Utilitarios {
    
        /*
     Metodo para converter caracteres acentuados no seu equivalente em html
     */
    public static String acentuarCaractersHtml(String plainString) {
        String htmlEntitiesString = "";

        for (int i = 0; i <= plainString.length() - 1; i++) {
            htmlEntitiesString += acentuarCaracters(plainString.charAt(i));
        }

        return htmlEntitiesString;
    }

    
    /**
     * 
     * @param ch
     * @return 
     * Metodo utilizado para colocar a acentuação correcta nas palavras
     * 
     */
    
    private static String acentuarCaracters(char ch) {

        String entityCode = "";
        String agudo = "&#769;";
        String grave = "&#768;";
        String circunflexo = "&#770;";
        String til = "&#771;";

        switch (ch) {
            case 'á':
                entityCode = "a" + agudo;
                break;
            case 'à':
                entityCode = "a" + grave;
                break;
            case 'Á':
                entityCode = "A" + agudo;
                break;
            case 'À':
                entityCode = "A" + grave;
                break;
            case 'ã':
                entityCode = "a" + til;
                break;
            case 'â':
                entityCode = "a" + circunflexo;
                break;
            case 'é':
                entityCode = "e" + agudo;
                break;
            case 'ê':
                entityCode = "e" + circunflexo;
                break;
            case 'É':
                entityCode = "E" + agudo;
                break;
            case 'Ê':
                entityCode = "E" + circunflexo;
                break;
            case 'ó':
                entityCode = "o" + agudo;
                break;
            case 'Ó':
                entityCode = "O" + agudo;
                break;
            case 'ò':
                entityCode = "o" + grave;
                break;
            case 'Ò':
                entityCode = "O" + grave;
                break;
            case 'õ':
                entityCode = "o" + til;
                break;
            case 'Õ':
                entityCode = "O" + til;
                break;
            case 'ô':
                entityCode = "o" + circunflexo;
                break;
            case 'Ô':
                entityCode = "O" + circunflexo;
                break;
            case 'ú':
                entityCode = "u" + agudo;
                break;
            case 'Ú':
                entityCode = "U" + agudo;
                break;
            case 'í':
                entityCode = "i" + agudo;
                break;
            case 'Í':
                entityCode = "I" + agudo;
                break;
            case 'ç':
                entityCode = "&ccedil;";
                break;
            case 'Ç':
                entityCode = "&Ccedil;";
                break;
            default:
                entityCode = "" + ch;
        }

        return entityCode;

    }
}
