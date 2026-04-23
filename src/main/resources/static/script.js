// ================= BASE URL =================
const BASE_URL = "http://localhost:8080";

// ================= AUTH CHECK =================
function checkAuth(allowedRoles = []) {
    const userRole = localStorage.getItem('userRole');
    const userEmail = localStorage.getItem('userEmail');

    if (!userRole || !userEmail) {
        window.location.href = 'login.html';
        return null;
    }

    if (allowedRoles.length > 0 && !allowedRoles.includes(userRole)) {
        if (userRole === 'student') window.location.href = 'student.html';
        if (userRole === 'teacher') window.location.href = 'teacher.html';
        if (userRole === 'admin') window.location.href = 'admin.html';
        return null;
    }

    return { userRole, userEmail };
}

// ================= LOGOUT =================
function logout() {
    localStorage.removeItem('userRole');
    localStorage.removeItem('userEmail');
    window.location.href = 'login.html';
}

// ================= COMMON API FUNCTION =================
async function apiFetch(endpoint, method = "GET", data = null) {
    try {
        const res = await fetch(BASE_URL + endpoint, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: data ? JSON.stringify(data) : null
        });

        if (!res.ok) {
            throw new Error("API Error");
        }

        return await res.json();
    } catch (err) {
        console.error("API Error:", err);
        alert("Server se connect nahi ho pa raha");
        return null;
    }
}

// ================= CREATE EVENT (TEACHER) =================
async function createEvent(eventData) {
    const res = await apiFetch("/createEvent", "POST", eventData);
    return res;
}

// ================= GET ALL EVENTS =================
async function getAllEvents() {
    return await apiFetch("/events");
}

// ================= REGISTER EVENT (STUDENT) =================
async function registerEvent(eventName) {
    const email = localStorage.getItem('userEmail');

    const res = await apiFetch("/register", "POST", {
        eventName: eventName,
        userEmail: email
    });

    if (res) {
        alert(eventName + " mein successfully register ho gaye!");
    } else {
        alert("Registration fail ho gayi");
    }
}

// ================= LOAD TEACHER EVENTS =================
async function loadMyEvents() {
    const { userEmail } = checkAuth(['teacher']) || {};

    const data = await getAllEvents();
    if (!data) return;

    console.log("All events:", data);

    // filter by teacher email
    const myEvents = data.filter(e => e.createdByEmail === userEmail);

    const tableBody = document.getElementById('myEventsTable');

    if (!tableBody) return;

    tableBody.innerHTML = myEvents.map(event => `
        <tr>
            <td><strong>${event.name}</strong></td>
            <td>${event.category}</td>
            <td>${event.eventDate}</td>
            <td>${event.status || "PENDING"}</td>
        </tr>
    `).join('');

    if (myEvents.length === 0) {
        tableBody.innerHTML = `<tr><td colspan="4">No events found</td></tr>`;
    }
}

async function approveEvent(id) {
    await apiFetch(`/approve/${id}`, "PUT");
    alert("Approved");
    loadAllEvents();
}

async function rejectEvent(id) {
    await apiFetch(`/reject/${id}`, "PUT");
    alert("Rejected");
    loadAllEvents();
}

// ================= GLOBAL EXPORT =================
window.checkAuth = checkAuth;
window.logout = logout;
window.apiFetch = apiFetch;
window.createEvent = createEvent;
window.registerEvent = registerEvent;
window.loadMyEvents = loadMyEvents;