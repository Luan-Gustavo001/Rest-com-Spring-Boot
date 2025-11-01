package com.gustavo.luan.repository;

import com.gustavo.luan.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
