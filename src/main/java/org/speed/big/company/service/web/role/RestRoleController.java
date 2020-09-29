package org.speed.big.company.service.web.role;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestRoleController.REST_URL)
public class RestRoleController extends AbstractRoleController{
    static final String REST_URL = "rest/roles";

}
