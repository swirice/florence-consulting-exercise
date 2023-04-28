package com.exercise.demo.attachment;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.exercise.demo.dto.UserDTO;
import com.exercise.demo.exception.ParsingException;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CsvManager {

	public static List<UserDTO> userCsvToList(MultipartFile file) {

		try {
			Reader reader = new InputStreamReader(file.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build(); // Skip the header
			List<UserDTO> output = new CsvToBeanBuilder<UserDTO>(csvReader).withType(UserDTO.class)
					.withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).build().parse();
			log.info("userCsvToList(MultipartFile) - File parsed successfully!");

			return output;
		} catch (IOException e) {
			String message = String.format("Error while parsing file %s", file.getName());
			log.error("userCsvToList(MultipartFile) - {}", message);
			throw new ParsingException(message);
		}

	}

}
