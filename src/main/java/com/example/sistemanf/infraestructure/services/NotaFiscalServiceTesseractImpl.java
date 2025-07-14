package com.example.sistemanf.infraestructure.services;

import com.example.sistemanf.datasources.NotaFiscalDataSource;
import com.example.sistemanf.infraestructure.exceptions.LeituraValorNotaFiscalException;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotaFiscalServiceTesseractImpl implements NotaFiscalDataSource {

    private static final String VALOR_NOTA_FISCAL_REGEX = "\\d{1,3}(?:\\.\\d{3})*(?:,\\d+)?";

    @Override
    public Double getValorTotal(File file) {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("por");

        try {
            BufferedImage image = ImageIO.read(file);
            String textoNotaFiscal = tesseract.doOCR(image);
            return extrairValorTotal(textoNotaFiscal);
        }
        catch (IOException | TesseractException e) {
            throw new LeituraValorNotaFiscalException(e.getMessage());
        }
    }

    private Double extrairValorTotal(String textoNotaFiscal) {
        Pattern pattern = Pattern.compile(VALOR_NOTA_FISCAL_REGEX);
        Matcher matcher = pattern.matcher(textoNotaFiscal);
        while (matcher.find()) {
            String match = matcher.group();
            if (textoNotaFiscal.substring(0, matcher.start()).toUpperCase().contains("TOTAL"))
                return Double.valueOf(match); //TODO testar
        }

        throw new LeituraValorNotaFiscalException("não foi possível identificar o valor da nota fiscal.");
    }
}
