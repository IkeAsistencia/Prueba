package com.ike.service.banbajio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ike.service.banbajio.cancellationService.dto.ResponseCancellationService;
import com.ike.service.banbajio.cancellationService.dto.cancellation;
import com.ike.service.banbajio.cancellationService.service.ServiceCancellationService;
import com.ike.service.banbajio.generateInstructionCollection.dto.ResponseInstruccionCobro;
import com.ike.service.banbajio.generateInstructionCollection.dto.generateInstruction;
import com.ike.service.banbajio.generateInstructionCollection.service.ServiceGenerateInstructionCollection;
import com.ike.service.banbajio.getCustomersProspects.dto.ResponseProspects;
import com.ike.service.banbajio.getCustomersProspects.dto.costumersProspects;
import com.ike.service.banbajio.getCustomersProspects.service.ServiceGetCostumersProspects;
import com.ike.service.banbajio.registerAttendanceUsage.dto.ResponseRegisterAttendance;
import com.ike.service.banbajio.registerAttendanceUsage.dto.registerAttendance;
import com.ike.service.banbajio.registerAttendanceUsage.service.ServiceRegisterAttendanceUsage;
import com.ike.service.banbajio.registerCustomerService.dto.ResponseRegisterCustomer;
import com.ike.service.banbajio.registerCustomerService.dto.registerCustomer;
import com.ike.service.banbajio.registerCustomerService.service.ServiceRegisterCustomerService;
import com.ike.service.cognito.service.ServiceValidToken;
import com.ike.service.common.exceptions.BanBanjioException;
import com.ike.service.common.validate.ValidFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/api/customer")
@Api(description = "Banbajio services, controlled by API' HUB middleware", tags = "Services API' HUB")
public class BanbajioController {

	@Autowired
	ServiceCancellationService cancellationService;
	@Autowired
	ServiceGenerateInstructionCollection serviceGenerateInstructionCollection;
	@Autowired
	ServiceGetCostumersProspects serviceGetCostumersProspects;
	@Autowired
	ServiceRegisterAttendanceUsage serviceRegisterAttendanceUsage;
	@Autowired 
	ServiceRegisterCustomerService serviceRegisterCustomerService;
	@Autowired
	ServiceValidToken serviceValidToken;
	
	ValidFormat validFormat = null;
	
	@ApiOperation(value = "Obtain Prospective Customers")
	@PostMapping(value = "/costumerProspects")
	public ResponseEntity<Object> getCostumersProspects(@RequestBody costumersProspects costumersProspects,
			                                            @RequestHeader("Authorization") String token) {

		try {
			if (serviceValidToken.validToken(token)) {
				ResponseProspects dto_prospectos = serviceGetCostumersProspects.getCostumersProspects();
				if (dto_prospectos.getCodigoRespuesta().equals("0000")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.OK);
				} else if (dto_prospectos.getCodigoRespuesta().equals("9999")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				} else if (dto_prospectos.getCodigoRespuesta().equals("8001")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				} else if (dto_prospectos.getCodigoRespuesta().equals("8002")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				} else if (dto_prospectos.getCodigoRespuesta().equals("8003")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				} else if (dto_prospectos.getCodigoRespuesta().equals("8004")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				} else if (dto_prospectos.getCodigoRespuesta().equals("8005")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				}  else if (dto_prospectos.getCodigoRespuesta().equals("8006")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				}else if (dto_prospectos.getCodigoRespuesta().equals("1111")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				} else if (dto_prospectos.getCodigoRespuesta().equals("1001")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				} else if (dto_prospectos.getCodigoRespuesta().equals("1002")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				} else if (dto_prospectos.getCodigoRespuesta().equals("1003")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				} else if (dto_prospectos.getCodigoRespuesta().equals("1004")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				} else if (dto_prospectos.getCodigoRespuesta().equals("1005")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				} else if (dto_prospectos.getCodigoRespuesta().equals("7001")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				} else if (dto_prospectos.getCodigoRespuesta().equals("7002")) {
					return new ResponseEntity<>(dto_prospectos, HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<>("TOKEN INVALID!",HttpStatus.UNAUTHORIZED);
		} catch (BanBanjioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Generate a payment instruction")
	@PostMapping(value = "/generateInstructionCollection")
	public ResponseEntity<Object> generateInstructionCollection(@Valid @RequestBody generateInstruction generateInstruction, BindingResult bindingResult,
			                                                    @RequestHeader("Authorization") String token) {

		validFormat = new ValidFormat();

		if (bindingResult.hasErrors()) {
			String errors = "";
			for (Object object : bindingResult.getAllErrors()) {
			    if(object instanceof ObjectError) {
			        errors = errors + ((ObjectError)object).getDefaultMessage() + ", ";
			    }
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
		
		if (!validFormat.dateFormatYearMonth(generateInstruction.getPetition2().getDescription())) {
			return new ResponseEntity<>("The date format is not correct.", HttpStatus.BAD_REQUEST);
		}

		try {
			if (serviceValidToken.validToken(token)) {
				ResponseInstruccionCobro json_costumers = serviceGenerateInstructionCollection.generateInstructionCollection(generateInstruction);
				if (json_costumers.getCodigoRespuesta().equals("0000")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.OK);
				} else if (json_costumers.getCodigoRespuesta().equals("9999")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8001")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8002")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8003")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8004")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8005")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8006")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1111")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1001")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1002")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1003")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1004")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1005")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("7001")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("7002")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1201")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<>("INVALID TOKEN", HttpStatus.UNAUTHORIZED);
		} catch (BanBanjioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Cancel service")
	@PostMapping(value = "/cancellationService")
	public ResponseEntity<Object> cancellationService(@Valid @RequestBody cancellation cancellation, BindingResult bindingResult,
			                                          @RequestHeader("Authorization") String token) {
		validFormat = new ValidFormat();
		
		if (bindingResult.hasErrors()) {
			String errors = "";
			for (Object object : bindingResult.getAllErrors()) {
			    if(object instanceof ObjectError) {
			        errors = errors + ((ObjectError)object).getDefaultMessage() + ", ";
			    }
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
		
		if (!validFormat.date(cancellation.getPetition1().getCancellationDate())) {
			return new ResponseEntity<>("The date format is not correct.", HttpStatus.BAD_REQUEST);
		}
		
		try {
			if (serviceValidToken.validToken(token)) {
				ResponseCancellationService json_costumers = cancellationService.cancellationService(cancellation);
				if (json_costumers.getCodigoRespuesta().equals("0000")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.OK);
				} else if (json_costumers.getCodigoRespuesta().equals("9999")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8001")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8002")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8003")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8004")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8005")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8006")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1111")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1001")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1002")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1003")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1004")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1005")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("7001")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("7002")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1201")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<>("INVALID TOKEN", HttpStatus.UNAUTHORIZED);
		} catch (BanBanjioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Register Attendance Usage")
	@PostMapping(value = "/frequencyOfUse")
	public ResponseEntity<Object> registerAttendanceUsage(@Valid @RequestBody registerAttendance registerAttendance, BindingResult bindingResult,
			                                              @RequestHeader("Authorization") String token) {

		if (bindingResult.hasErrors()) {
			String errors = "";
			for (Object object : bindingResult.getAllErrors()) {
			    if(object instanceof ObjectError) {
			        errors = errors + ((ObjectError)object).getDefaultMessage() + ", ";
			    }
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
		
		System.out.println("===" + registerAttendance.getPetition3().getCity());
        validFormat = new ValidFormat();
		
		if (!validFormat.date(registerAttendance.getPetition3().getDateAssistanceRequest())) {
			return new ResponseEntity<>("The date format is not correct.", HttpStatus.BAD_REQUEST);
		}
		
		try {
			if (serviceValidToken.validToken(token)) {
				ResponseRegisterAttendance json_costumers = serviceRegisterAttendanceUsage.registerAttendanceUsage(registerAttendance);
				if (json_costumers.getCodigoRespuesta().equals("0000")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.OK);
				} else if (json_costumers.getCodigoRespuesta().equals("9999")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8001")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8002")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8003")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8004")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8005")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8006")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1111")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1001")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1002")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1003")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1004")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1005")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("7001")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("7002")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1201")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<>("INVALID TOKEN", HttpStatus.UNAUTHORIZED);
		} catch (BanBanjioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Register Customer Service")
	@PostMapping("/registerCustomerService")
	public ResponseEntity<Object> registerService(@Valid @RequestBody registerCustomer registerCustomer, BindingResult bindingResult,
			                                       @RequestHeader("Authorization") String token) {

		if (bindingResult.hasErrors()) {
			String errors = "";
			for (Object object : bindingResult.getAllErrors()) {
			    if(object instanceof ObjectError) {
			        errors = errors + ((ObjectError)object).getDefaultMessage() + ", ";
			    }
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
		
//		validFormat = new ValidFormat();
//		
//		if (!validFormat.date(registerCustomer.getPetition4().getDateRecruitment())) {
//			return new ResponseEntity<>("The date format is not correct.", HttpStatus.BAD_REQUEST);
//		}
		
		try {
			if (serviceValidToken.validToken(token)) {
				ResponseRegisterCustomer json_costumers = serviceRegisterCustomerService.registerCustomerService(registerCustomer);
				if (json_costumers.getCodigoRespuesta().equals("0000")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.OK);
				} else if (json_costumers.getCodigoRespuesta().equals("9999")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8001")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8001")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8002")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8003")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8004")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8005")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("8006")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1111")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1001")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1002")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1003")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1004")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1005")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("7001")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("7002")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				} else if (json_costumers.getCodigoRespuesta().equals("1201")) {
					return new ResponseEntity<>(json_costumers, HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<>("INVALID TOKEN", HttpStatus.UNAUTHORIZED);
		} catch (BanBanjioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
