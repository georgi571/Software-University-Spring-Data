package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.JobSeedDTO;
import softuni.exam.models.dto.xml.JobSeedRootDTO;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.Job;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.JobRepository;
import softuni.exam.service.JobService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final String PATH_INPUT = "src/main/resources/files/xml/jobs.xml";

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.jobRepository.count() > 0;
    }

    @Override
    public String readJobsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importJobs() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        JobSeedRootDTO jobSeedRootDTO = this.xmlParser.parse(JobSeedRootDTO.class, PATH_INPUT);
        for (JobSeedDTO jobSeedDTO : jobSeedRootDTO.getJobSeedDTOList()) {
            if (!this.validationUtil.isValid(jobSeedDTO)) {
                stringBuilder.append("Invalid job").append(System.lineSeparator());
                continue;
            }
            Job job = this.modelMapper.map(jobSeedDTO, Job.class);
            Company company = this.companyRepository.findById(jobSeedDTO.getCompanyId()).get();
            job.getCompanies().add(company);
            this.jobRepository.saveAndFlush(job);
            stringBuilder.append(String.format("Successfully imported job %s", job.getTitle())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String getBestJobs() {
        return this.jobRepository.findAllBySalaryGreaterThanEqualAndHoursAWeekLessThanEqualOrderBySalaryDesc(5000.00, 30.00)
                .stream()
                .map(job -> String.format("Job title %s\n" +
                                "-Salary: %.2f$\n" +
                                "--Hours a week: %.2fh.\n\n",
                        job.getTitle(),
                        job.getSalary(),
                        job.getHoursAWeek()))
                .collect(Collectors.joining());
    }
}
