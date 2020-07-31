package com.example.demo;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Controller
public class CsvController {

    @Autowired
    DownloadHelper downloadHelper;
    @Autowired
	UserService userService;

    /**
     * CsvMapperで、csvを作成する。
     * @return csv(String)
     * @throws JsonProcessingException
     */
    public String getCsvText() throws JsonProcessingException {
        CsvMapper mapper = new CsvMapper();
        //ヘッダをつける
        CsvSchema schema = mapper.schemaFor(Member.class).withHeader();

        List<Member> members = userService.FindALL();
        members.add(new Member());
        return mapper.writer(schema).writeValueAsString(members);
    }


    /**
     * csvをダウンロードする。
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/download/csv", method = RequestMethod.POST)
    public ResponseEntity<byte[]> download() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        downloadHelper.addContentDisposition(headers, "DB情報.csv");
        return new ResponseEntity<>(getCsvText().getBytes("MS932"), headers, HttpStatus.OK);
    }

}
