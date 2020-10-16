package by.svirski.testweb.service;

import by.svirski.testweb.service.exception.ServiceException;

public interface CustomSiteService {

	int countUsers() throws ServiceException;
}
