package assessment.juniorpost.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmployeInfoService {

    @Autowired
    private EmployeInfoRepository employeInfoRepository;

    private static final int BATCH_SIZE = 1000;


    public void storeData(MultipartFile file) {


        // you can increase thread size if you want
        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<String> linesBatch = new ArrayList<>();

        try {
            File file1 = convertMultiPartToFile(file);
            BufferedReader br = new BufferedReader(new FileReader(file1));
            String line;

            String header = br.readLine();

                while ((line = br.readLine()) != null) {

                    linesBatch.add(line);
                    if (linesBatch.size() == BATCH_SIZE) {
                        executor.submit(new EmployeeTaskExecutor(employeInfoRepository,new ArrayList<>(linesBatch)));
                        linesBatch.clear();
                    }
                }
                if (!linesBatch.isEmpty() && linesBatch.size() < BATCH_SIZE) {
                    executor.submit(new EmployeeTaskExecutor(employeInfoRepository,new ArrayList<>(linesBatch)));
                    linesBatch.clear();
                }
                executor.shutdown();
                file1.delete();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // convert multipartFile to File
    public File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile("temp", null);
        try (java.io.OutputStream os = new java.io.FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        }
        return file;
    }

    public List<EmployeInfo> findAll() {
        return employeInfoRepository.findAll();
    }

}
