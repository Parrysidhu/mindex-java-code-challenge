package com.mindex.challenge.service;

import java.util.Optional;

import com.mindex.challenge.data.Compensation;

public interface CompensationService {
	Compensation create(Compensation compensation);
	Optional<Compensation> read(String id);
}
