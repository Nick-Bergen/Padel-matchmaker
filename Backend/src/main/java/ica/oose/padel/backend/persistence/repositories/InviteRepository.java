package ica.oose.padel.backend.persistence.repositories;

import ica.oose.padel.backend.persistence.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InviteRepository extends JpaRepository <Invite, String> {
}
