package com.nexcommerce.hotel.respositories;

import com.nexcommerce.hotel.entites.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
