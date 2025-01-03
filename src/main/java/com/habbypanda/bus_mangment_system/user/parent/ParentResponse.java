package com.habbypanda.bus_mangment_system.user.parent;

import com.habbypanda.bus_mangment_system.utils.DTOMapper;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
@Data
public class ParentResponse {
    private String message;
    private HttpStatus status;
    private ParentDTO parent;
    private List<ParentDTO> parents;

    public ParentResponse(String message, HttpStatus status, Parent parent) {
        this.message = message;
        this.status = status;
        this.parent = DTOMapper.toParentDTO(parent);
    }
    public ParentResponse(String message, HttpStatus status, List<Parent> parents) {
        this.message = message;
        this.status = status;
        this.parents = DTOMapper.toParentDTOList(parents);
    }
    public ParentResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

}
