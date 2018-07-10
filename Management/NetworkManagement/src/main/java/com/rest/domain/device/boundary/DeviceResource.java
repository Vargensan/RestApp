package com.rest.domain.device.boundary;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;

import com.api.networkmanagement.DeviceDTO;
import com.rest.domain.device.control.DeviceMapper;
import com.rest.domain.device.control.DeviceRepository;
import com.rest.domain.device.control.NotificationService;
import com.rest.domain.device.entity.DeviceEntity;

@Stateless
@Consumes(MediaType.APPLICATION_JSON)

@Produces(MediaType.APPLICATION_JSON)
@Path(DeviceResource.MAIN_PATH)
public class DeviceResource {

	public static final String MAIN_PATH = "/device";
	private static final String ID = "id";
	private static final String ID_PATH = "/{" + ID + "}";
	private static final String DEVICE = "dev";
	private static final String DEVICE_PATH = "/{"+DEVICE+"}"+ID_PATH;

	@Inject
	private DeviceRepository repository;

	@Inject
	private NotificationService notificationService;
	
	@Inject
	private DeviceMapper deviceMapper;

	@Context
	private UriInfo uriInfo;
	
	@Inject
	private Logger logger;

	@Path(ID_PATH)
	@GET
	public DeviceDTO getDevice(@PathParam(ID) int deviceId) {
		logger.info("Get device with id: " + deviceId);
		DeviceEntity foundDevice = repository.getDeviceById(deviceId);
		return deviceMapper.mapToDTO(foundDevice);
	}
	
	@GET
	public List<DeviceDTO> getDevice() {
		List<DeviceEntity> foundDevice = repository.queryDeviceByQueryParams(uriInfo.getQueryParameters());
		return foundDevice.stream()
				.map(deviceMapper::mapToDTO)
				.collect(Collectors.toList());
	}

	@POST
	public Response createDevice(DeviceDTO device) {
		DeviceEntity deviceEntity = deviceMapper.mapToEntity(device);
		String id = String.valueOf(repository.save(deviceEntity));
		repository.setIdentifiersForDevice(deviceEntity);
		notificationService.notifySubsystems("CREATE: " + id);
		return Response.ok()
				.entity(id)
				.build();
	}
	
	
	@Path(ID_PATH)
	@PUT
	public Response updateDevice(@PathParam(ID) int deviceId, DeviceDTO device) {
		if(deviceId == 0) 
			throw new IllegalStateException();
		DeviceEntity newDevice = deviceMapper.mapToEntity(device);
		DeviceEntity foundDevice = repository.getDeviceById(deviceId);
		foundDevice.updateDevice(newDevice);
		notificationService.notifySubsystems("UPDATE: " + foundDevice.getId());
		return Response.ok()
				.entity(deviceId)
				.build();
	}
	
	@Path(ID_PATH)
	@DELETE
	public Response updateDevice(@PathParam(ID) int deviceId) {
		repository.deleteDevice(deviceId);
		notificationService.notifySubsystems("DELETE: " + deviceId);
		return Response.ok()
				.build();
	}
	
}