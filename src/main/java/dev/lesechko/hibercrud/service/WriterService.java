package dev.lesechko.hibercrud.service;

import dev.lesechko.hibercrud.model.Writer;
import dev.lesechko.hibercrud.repository.WriterRepository;
import dev.lesechko.hibercrud.repository.hibernate.HibernateWriterRepositoryImpl;


import java.util.List;

public class WriterService {
    WriterRepository writerRepository; //= new WriterRepositoryImpl();

    public WriterService() {
        writerRepository = new HibernateWriterRepositoryImpl();
    }

    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public Writer save(Writer writer) {
        return writerRepository.save(writer);
    }

    public List<Writer> getAll() {
        return writerRepository.getAll();
    }

    public Writer getById(Long id) {
        return writerRepository.getById(id);
    }

    public Writer update(Writer writer) {
        return writerRepository.update(writer);
    }

    public boolean deleteById(Long id) {
        return writerRepository.deleteById(id);
    }
}
