/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.co.a2x.excel;

import ao.co.a2x.model.Pessoa;
import ao.co.a2x.model.Product;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author a2x
 */
public class Excel {

    public static void main(String[] args) {
        try {
            escreverXlsx(new File("C:\\Users\\a2x\\Documents\\projectos\\2019\\CodigoFonte\\web\\resources\\Office\\excel\\produtos.xlsx"));
            List<Pessoa> Pessoas = lerxlsx(new FileInputStream(new File("C:\\Users\\a2x\\Documents\\projectos\\2019\\CodigoFonte\\web\\resources\\Office\\excel\\pessoas.xlsx")));
            for (Pessoa p : Pessoas) {
                System.out.println("Nome: " + p.getNome() + " Idade: " + p.getIdade());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Pessoa> lerxlsx(InputStream file) throws IOException, InvalidFormatException, IllegalArgumentException {

        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        List<Pessoa> pessoas = new ArrayList<>();
        Pessoa pessoa;
        for (Row row : sheet) {
            pessoa = new Pessoa();
            // LISTA DE ALUNOS 
            if (row.getRowNum() > 0) {  // Lê os dados a partir da linha 1 ingnorando a linha 0
                for (Cell cell : row) {
                    switch (cell.getColumnIndex()) {
                        case 0:
                            pessoa.setNome(cell.getStringCellValue());
                            break;
                        case 1:
                            pessoa.setIdade((int) cell.getNumericCellValue());
                            break;
                    }
                }
                pessoas.add(pessoa);
            }
        }
        return pessoas;
    }

    private static List getProducts() {
        List products = new ArrayList();
        products.add(new Product(1l, "Produto 1", 200.5d));
        products.add(new Product(2l, "Produto 2", 1050.5d));
        products.add(new Product(3l, "Produto 3", 50d));
        products.add(new Product(4l, "Produto 4", 200d));
        products.add(new Product(5l, "Produto 5", 450d));
        products.add(new Product(6l, "Produto 6", 150.5d));
        products.add(new Product(7l, "Produto 7", 300.99d));
        products.add(new Product(8l, "Produto 8", 1000d));
        products.add(new Product(9l, "Produto 9", 350d));
        products.add(new Product(10l, "Produto 10", 200d));

        return products;
    }

    public static void escreverXlsx(File file) throws IOException, InvalidFormatException {
// Criando o arquivo e uma planilha chamada "Product"
        Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
        Sheet sheet = workbook.getSheetAt(0);

// Definindo alguns padroes de layout
        sheet.setDefaultColumnWidth(15);
        sheet.setDefaultRowHeight((short) 400);

//Carregando os produtos
        List<Product> products = getProducts();

        int rownum = 0;
        int cellnum = 0;
        Cell cell;
        Row row;

//Configurando estilos de células (Cores, alinhamento, formatação, etc..)
        DataFormat numberFormat = workbook.createDataFormat();

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        CellStyle textStyle = workbook.createCellStyle();
        textStyle.setAlignment(CellStyle.ALIGN_CENTER);
        textStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        CellStyle numberStyle = workbook.createCellStyle();
        numberStyle.setDataFormat(numberFormat.getFormat("#,##0.00"));
        numberStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

// Configurando Header
        row = sheet.createRow(rownum++);
        cell = row.createCell(cellnum++);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Code");

        cell = row.createCell(cellnum++);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Name");

        cell = row.createCell(cellnum++);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Price");

// Adicionando os dados dos produtos na planilha
        for (Product product : products) {
            row = sheet.createRow(rownum++);
            cellnum = 0;

            cell = row.createCell(cellnum++);
            cell.setCellStyle(textStyle);
            cell.setCellValue(product.getId());

            cell = row.createCell(cellnum++);
            cell.setCellStyle(textStyle);
            cell.setCellValue(product.getName());

            cell = row.createCell(cellnum++);
            cell.setCellStyle(numberStyle);
            cell.setCellValue(product.getPrice());
        }

        try {

            //Escrevendo o arquivo em disco
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            System.out.println("Success!!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
