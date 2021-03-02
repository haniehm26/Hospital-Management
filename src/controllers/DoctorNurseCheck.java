package controllers;

import java.io.IOException;

public abstract class DoctorNurseCheck {

    protected abstract boolean nurseCheck() throws IOException;

    protected abstract boolean doctorCheck() throws IOException;
}
