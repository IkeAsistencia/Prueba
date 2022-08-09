package com.ike.service.banbajio.generateInstructionCollection.service;

import com.ike.service.banbajio.generateInstructionCollection.dto.ResponseInstruccionCobro;
import com.ike.service.banbajio.generateInstructionCollection.dto.generateInstruction;
import com.ike.service.common.exceptions.BanBanjioException;

public interface ServiceGenerateInstructionCollection {

	public ResponseInstruccionCobro generateInstructionCollection(generateInstruction generateInstruction) throws BanBanjioException;
}
