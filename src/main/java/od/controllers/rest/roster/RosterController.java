/**
 * 
 */
package od.controllers.rest.roster;

import java.util.Map;
import java.util.Set;

import od.model.roster.Member;
import od.providers.roster.RosterProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ggilbert
 *
 */
@RestController
public class RosterController {
	
	private static final Logger log = LoggerFactory.getLogger(RosterController.class);
	
	@Autowired private RosterProvider rosterProvider;
	
	@Secured("ROLE_INSTRUCTOR")
	@RequestMapping(value = "/api/{contextMappingId}/db/{dashboardId}/card/{cardId}/roster", method = RequestMethod.POST)
	public Set<Member> roster(
			@PathVariable("contextMappingId") String contextMappingId,
			@PathVariable("dashboardId") String dashboardId,
			@PathVariable("cardId") String cardId,
			@RequestBody Map<String, String> options)
			throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("contextMappingId " + contextMappingId);
			log.debug("dashboardId " + dashboardId);
			log.debug("cardId " + cardId);
			log.debug("options " + options);
		}
		
		return rosterProvider.getRoster(options.get("ext_ims_lis_memberships_url"), options.get("ext_ims_lis_memberships_id"));
	}
}
