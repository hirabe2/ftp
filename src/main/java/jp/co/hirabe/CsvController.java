package jp.co.hirabe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("csv")
public class CsvController {

    @GetMapping(value = "*.csv",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=Shift_JIS; Content-Disposition: attachment")
    public String download() throws JsonProcessingException {
        List<Demo> demos = new ArrayList<Demo>() {
            {
                /*
                add(new Demo("1001", "aaa", "xxx", "1536155296397"));
                add(new Demo("1001", "bbb", "yyy", "1536155296397"));
                add(new Demo("1001", "ccc", "zzz", "1536155296397"));
                */
            }
        };

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(Demo.class).withHeader();

        return mapper.writer(schema).writeValueAsString(demos);
    }

    @GetMapping(value = "parse")
    public String parse() throws IOException {
        String lineSeparator = System.getProperty("line.separator");
        String input = "ID,概要,名前,更新日時" + lineSeparator +
                "1001L,xxx,aaa,1536155736739" + lineSeparator +
                "1002L,yyy,bbb,1536155736739" + lineSeparator +
                "1002L,bbb,1536155736739" + lineSeparator +
                "1003L,zzz,ccc,1536155736739";

        CsvMapper mapper = new CsvMapper();
        //CsvSchema schema = mapper.schemaFor(Demo.class);
        CsvSchema schema = CsvSchema.builder()
                .addColumn("id", CsvSchema.ColumnType.NUMBER_OR_STRING)
                .addColumn("name", CsvSchema.ColumnType.STRING)
                .addColumn("desc", CsvSchema.ColumnType.STRING)
                .addColumn("modified", CsvSchema.ColumnType.STRING)
                .build()
                .withHeader();

        MappingIterator<Demo> it = mapper.readerFor(Demo.class).with(schema).readValues(input);
                // mapper.reader(Demo.class).with(schema).readValues(input);
        List<Demo> list = new ArrayList<Demo>();
        System.out.println(it.readAll());

        while (it.hasNext()){
            Demo row = it.next();
            list.add(row);
        }

        return list.toString();
    }
}
