package com.example.userlap.servicecenter;

public class service {
    public int ServiceId;
    public String ServiceName;
    public String Service_contact;
    public String Location;

    public service(int serviceId, String serviceName, String service_contact, String location) {
        ServiceId = serviceId;
        ServiceName = serviceName;
        Service_contact = service_contact;
        Location = location;
    }
    public service() {

    }

    public int getServiceId() {
        return ServiceId;
    }

    public void setServiceId(int serviceId) {
        ServiceId = serviceId;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getService_contact() {
        return Service_contact;
    }

    public void setService_contact(String service_contact) {
        Service_contact = service_contact;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }





}
