package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Integer> {
}
