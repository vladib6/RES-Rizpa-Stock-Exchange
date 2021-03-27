package com;

import java.util.List;

public class UserDTO {//Data Transfer Object


    public UserDTO(int totalHoldings, String username, List<HoldingsDTO> holdingsDTOList) {
        this.totalHoldings = totalHoldings;
        this.username = username;
        this.holdingsDTOList = holdingsDTOList;
    }

    int totalHoldings;
    String username;
    List<HoldingsDTO> holdingsDTOList;

    public List<HoldingsDTO> getHoldingsDTOList(){return holdingsDTOList;}
}
