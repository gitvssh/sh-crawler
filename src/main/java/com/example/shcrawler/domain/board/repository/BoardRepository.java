package com.example.shcrawler.domain.board.repository;

import com.example.shcrawler.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
