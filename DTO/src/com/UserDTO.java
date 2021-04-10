package com;

import java.util.List;

public class UserDTO {//Data Transfer Object


    public UserDTO(int totalHoldings, String username, List<HoldingsDTO> holdingsDTOList) {
        this.totalHoldings = totalHoldings;
        this.username = username;
        this.holdingsDTOList = holdingsDTOList;
    }

    private final int totalHoldings;
    private final String username;
    private final List<HoldingsDTO> holdingsDTOList;


    public int getTotalHoldings() { return totalHoldings; }

    public String getUsername() { return username; }

    public List<HoldingsDTO> getHoldingsDTOList(){return holdingsDTOList;}

    public HoldingsDTO getHoldingsDtoByStock(String stock){
        for(HoldingsDTO dto: holdingsDTOList){
            if(dto.getSymbol().equals(stock)){
                return dto;
            }
        }
        return null;
    }
}
