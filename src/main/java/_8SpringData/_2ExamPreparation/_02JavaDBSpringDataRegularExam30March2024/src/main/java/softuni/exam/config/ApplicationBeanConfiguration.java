package softuni.exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    };

    @Bean
    public Gson gson() {
        return new Gson().newBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }



//    @Bean
//    public Gson gson() {
//        return new Gson().newBuilder()
//                .setPrettyPrinting()
//                .excludeFieldsWithoutExposeAnnotation()
//                .create();
//    }
//
//
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//
//        modelMapper.addConverter(new Converter<String, LocalDate>() {
//            @Override
//            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
//
//                if(mappingContext.getSource() != null) {
//                    LocalDate parse = LocalDate
//                            .parse(mappingContext.getSource(),
//                                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//                    return parse;
//                }
//                return null;
//            }
//        });
//
//        return modelMapper;
//    }
//
//    @Bean
//    public Validator validator() {
//        return Validation.buildDefaultValidatorFactory().getValidator();
//    }

}
