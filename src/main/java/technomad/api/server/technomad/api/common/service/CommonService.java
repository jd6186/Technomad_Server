package technomad.api.server.technomad.api.common.service;

import org.springframework.stereotype.Service;
import technomad.api.server.technomad.api.common.dto.response.LoginResponseDto;
import technomad.api.server.technomad.api.common.query.CommonQuery;

@Service
public class CommonService {
    private final CommonQuery commonQuery;

    public CommonService(CommonQuery commonQuery) {
        this.commonQuery = commonQuery;
    }

    public LoginResponseDto getUserLoginData(){
        return commonQuery.findUserLoginData();
    }

}
