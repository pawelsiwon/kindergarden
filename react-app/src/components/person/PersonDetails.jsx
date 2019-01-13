import React, { Component } from "react";
import Axios from "axios";

class PersonDetails extends Component {
  state = {
    edit: true,
    person: {
      name: "Pawel",
      lastname: "Siwoń"
    }
  };
  render() {
    return (
      <div className="container">
        <h3>
          {this.state.person.name + " " + this.state.person.lastname} details
        </h3>
        <form className="needs-validation">
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="firstName">First name</label>
              <input
                disabled={this.state.edit}
                id="firstName"
                className="form-control"
                type="text"
                value={this.state.name}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="lastName">Last name</label>
              <input
                disabled={this.state.edit}
                id="lastName"
                className="form-control"
                type="text"
                value={this.state.surname}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="birthdate">Birthdate</label>
              <input
                disabled={this.state.edit}
                type="date"
                id="birthdate"
                className="form-control"
                value={this.state.birthdate}
              />
            </div>
          </div>
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="001">Pesel</label>
              <input
                disabled={this.state.edit}
                type="text"
                id="001"
                className="form-control"
                value={this.state.pesel}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="002">Phone number</label>
              <input
                disabled={this.state.edit}
                type="text"
                id="003"
                className="form-control"
                value={this.state.role}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="003">Role</label>
              <select
                disabled={this.state.edit}
                type="text"
                id="002"
                className="custom-select d-block w-100"
                value={this.state.phonenumber}
              >
                <option value="">Choose role...</option>
              </select>
            </div>
          </div>
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="0001">E-mail</label>
              <input
                disabled={this.state.edit}
                type="email"
                className="form-control"
                id="0001"
                value={this.state.email}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="0002">Login</label>
              <input
                disabled={this.state.edit}
                type="text"
                className="form-control"
                id="0002"
                value={this.state.login}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="0003">Password</label>
              <input
                disabled={this.state.edit}
                type="password"
                className="form-control"
                id="0003"
                value={this.state.password}
              />
            </div>
          </div>
          <div className="row">
            <div className="col-md-3 mb-3">
              <label htmlFor="city">City</label>
              <input
                disabled={this.state.edit}
                type="text"
                id="city"
                className="form-control"
                value={this.state.city}
              />
            </div>
            <div className="col-md-2 mb-3">
              <label htmlFor="postale">Postale</label>
              <input
                disabled={this.state.edit}
                type="text"
                id="postale"
                pattern="[1-9]{2}-[0-9]{3}"
                placeholder="__-___"
                className="form-control"
                value={this.state.postale}
              />
            </div>
            <div className="col-md-5 mb-3">
              <label htmlFor="street">Street</label>
              <input
                disabled={this.state.edit}
                type="text"
                id="street"
                className="form-control"
                value={this.state.street}
              />
            </div>
            <div className="col-md-1 mb-3">
              <label htmlFor="apartment">Apartment</label>
              <input
                disabled={this.state.edit}
                type="text"
                pattern="[1-9]*"
                id="apartment"
                className="form-control"
                value={this.state.streetAddress}
              />
            </div>
            <div className="col-md-1 mb-3">
              <label htmlFor="suite">Suite</label>
              <input
                disabled={this.state.edit}
                type="text"
                pattern="[1-9]*[a-z]?"
                id="suite"
                className="form-control"
                value={this.state.suite}
              />
            </div>
          </div>
        </form>
      </div>
    );
  }

  getPersonData() {
    Axios.post(this.props.apiHost + "/", this.props.personId)
      .then((res, req) => console.log(res))
      .catch(err => console.log("NIE"));
  }

  componentWillMount() {
    this.getPersonData();
  }
}

export default PersonDetails;
