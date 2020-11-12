package by.svirski.testweb.service;

import by.svirski.testweb.service.exception.ServiceException;

public interface CustomSystemService {

	int countUsers() throws ServiceException;
}
