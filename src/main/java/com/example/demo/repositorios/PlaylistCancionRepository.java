package com.example.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.beans.CancionPlaylist;

public interface PlaylistCancionRepository extends JpaRepository<CancionPlaylist, Long>{

}
