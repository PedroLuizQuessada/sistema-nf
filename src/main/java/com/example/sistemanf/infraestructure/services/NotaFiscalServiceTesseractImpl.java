package com.example.sistemanf.infraestructure.services;

import com.example.sistemanf.datasources.NotaFiscalDataSource;
import com.example.sistemanf.infraestructure.exceptions.LeituraNotaFiscalException;
import com.example.sistemanf.utils.TimeUtil;
import jakarta.annotation.PostConstruct;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Profile("tesseract")
public class NotaFiscalServiceTesseractImpl implements NotaFiscalDataSource {

    private static final String VALOR_NOTA_FISCAL_REGEX = "\\d{1,3}(?:\\.\\d{3})*(?:,\\d+)?";
    private static final String DATA_EMISSAO_NOTA_FISCAL_REGEX = "\\d{2}/\\d{2}/\\d{4}";
    private static final String CNPJ_SERVICO_NOTA_FISCAL_REGEX = "[a-zA-Z0-9]{2}\\.[a-zA-Z0-9]{3}\\.[a-zA-Z0-9]{3}/[a-zA-Z0-9]{4}-[a-zA-Z0-9]{2}";

    @Value("${tesseract.traineddata}")
    private String tesseractTrainedData;

    @PostConstruct
    public void init() {
        System.setProperty("TESSDATA_PREFIX", tesseractTrainedData);
    }

    @Override
    public String getTexto(File file) {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("por");
        tesseract.setDatapath(System.getProperty("TESSDATA_PREFIX"));

        try {
            BufferedImage image = ImageIO.read(file);
            return tesseract.doOCR(image);
        }
        catch (IOException | TesseractException e) {
            throw new LeituraNotaFiscalException(e.getMessage());
        }
    }

    @Override
    public Double getValorTotal(String textoNotaFiscal) {
        Pattern pattern = Pattern.compile(VALOR_NOTA_FISCAL_REGEX);
        Matcher matcher = pattern.matcher(textoNotaFiscal);
        while (matcher.find()) {
            String match = matcher.group();
            if (textoNotaFiscal.substring(0, matcher.start()).toUpperCase().contains("TOTAL"))
                return Double.valueOf(match.replace(".", "").replace(",", "."));
        }

        throw new LeituraNotaFiscalException("não foi possível identificar o valor da nota fiscal.");
    }

    @Override
    public Date getDataEmissao(String textoNotaFiscal) {
        Pattern pattern = Pattern.compile(DATA_EMISSAO_NOTA_FISCAL_REGEX);
        Matcher matcher = pattern.matcher(textoNotaFiscal);
        if (matcher.find()) {
            String match = matcher.group();
            return TimeUtil.fromDataBrtoDate(match);
        }

        throw new LeituraNotaFiscalException("não foi possível identificar a data de emissão da nota fiscal.");
    }

    @Override
    public String getCnpjServico(String textoNotaFiscal) {
        Pattern pattern = Pattern.compile(CNPJ_SERVICO_NOTA_FISCAL_REGEX);
        Matcher matcher = pattern.matcher(textoNotaFiscal);
        while (matcher.find()) {
            String match = matcher.group();
            if (textoNotaFiscal.substring(0, matcher.start()).toUpperCase().contains("CNPJ"))
                return match.replace(".", "").replace("/", "").replace("-", "");
        }

        throw new LeituraNotaFiscalException("não foi possível identificar o CNPJ de serviço da nota fiscal.");
    }
}
