package com.example.newspipeline.controller.eco;

import com.example.newspipeline.domain.EcoNews;
import com.example.newspipeline.domain.LandNews;
import com.example.newspipeline.service.eco.EcoNewsService;
import com.example.newspipeline.service.land.LandNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * PackageName 	: com.example.newspipeline.controller.eco
 * FileName 	: EcoNewsController
 * Author 		: jhchoi
 * Date 		: 2023-11-09
 * Description 	:
 * ======================================================
 * DATE				    AUTHOR				NOTICE
 * ======================================================
 * 2023-11-09			jhchoi				최초 생성
 */
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
	private final EcoNewsService ecoNewsService;
	private final LandNewsService landNewsService;

	@GetMapping("/eco-all")
	public ResponseEntity<List<EcoNews>> findAllEcoNews() {
		return ResponseEntity.ok(ecoNewsService.findAll());
	}

	@GetMapping("/land-all")
	public ResponseEntity<List<LandNews>> findAllLandNews() {
		return ResponseEntity.ok(landNewsService.findAll());
	}
}
