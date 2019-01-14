import React, { Component } from "react";
import Axios from "axios";
import { withRouter } from "react-router";

class PersonDetails extends Component {
  state = {
    child: { people: [""] }
  };

  render() {
    return (
      <div className="container">
        <div className="row">
          <button
            className="btn btn-primary m-2"
            onClick={e => this.props.history.push("/children/show")}
          >
            Return
          </button>
        </div>
        <h3>
          {this.state.child.name + " " + this.state.child.surname} details
        </h3>
        <form className="needs-validation">
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="firstName">First name</label>
              <input
                disabled
                id="firstName"
                className="form-control"
                type="text"
                value={this.state.child.name}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="lastName">Last name</label>
              <input
                disabled
                id="lastName"
                className="form-control"
                type="text"
                value={this.state.child.surname}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="birthdate">Birthdate</label>
              <input
                disabled
                type="date"
                id="birthdate"
                className="form-control"
                value={this.state.child.birthdate}
              />
            </div>
          </div>
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="001">Pesel</label>
              <input
                disabled
                type="text"
                id="001"
                className="form-control"
                value={this.state.child.pesel}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="003">Admission date</label>
              <input
                disabled
                type="date"
                id="003"
                className="form-control"
                value={this.state.child.admissionDate}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="002">Discharge date</label>
              <input
                disabled
                type="date"
                id="002"
                className="form-control"
                value={this.state.child.dischargeDate}
              />
            </div>
          </div>
          <div className="row">
            <div className="col-md-3 mb-3">
              <label htmlFor="city">City</label>
              <input
                disabled
                type="text"
                id="city"
                className="form-control"
                value={this.state.child.city}
              />
            </div>
            <div className="col-md-2 mb-3">
              <label htmlFor="postale">Postale</label>
              <input
                disabled
                type="text"
                id="postale"
                pattern="[1-9]{2}-[0-9]{3}"
                placeholder="__-___"
                className="form-control"
                value={this.state.child.postale}
              />
            </div>
            <div className="col-md-5 mb-3">
              <label htmlFor="street">Street</label>
              <input
                disabled
                type="text"
                id="street"
                className="form-control"
                value={this.state.child.street}
              />
            </div>
            <div className="col-md-1 mb-3">
              <label htmlFor="apartment">Apartment</label>
              <input
                disabled
                type="text"
                pattern="[1-9]*"
                id="apartment"
                className="form-control"
                value={this.state.child.streetAddress}
              />
            </div>
            <div className="col-md-1 mb-3">
              <label htmlFor="suite">Suite</label>
              <input
                disabled
                type="text"
                pattern="[1-9]*[a-z]?"
                id="suite"
                className="form-control"
                value={this.state.child.suite}
              />
            </div>
          </div>
          <div className="row">
            {this.state.child.people.map(parent => (
              <div className="col-md-6" key={parent.id}>
                <label htmlFor="parent">Parent</label>
                <div className="input-group">
                  <input type="text" id="parent" className="form-control" />
                  <span className="input-group-btn btn-right">
                    <button
                      className="btn btn-light"
                      type="button"
                      onClick={() => this.props.history.push("/children/show")}
                    >
                      PARENT DETAILS
                    </button>
                  </span>
                </div>
              </div>
            ))}
          </div>
          <div className="row" />
          <button
            className="btn btn-warning m-2"
            onClick={e => this.goToPage("/child/edit/" + this.state.child.id)}
          >
            Edit
          </button>
        </form>
      </div>
    );
  }

  enableEdit = () => {
    this.setState({ edit: "true" });
  };

  getChildData = () => {
    const requestData = {
      loginData: this.props.session,
      childId: this.props.match.params.childId
    };
    Axios.post(this.props.apiHost + "/child/profile", requestData)
      .then((res, req) => {
        this.setState({ child: res.data });
      })
      .catch(err => console.log(err));
  };

  componentWillMount() {
    this.getChildData();
  }

  goToPage = path => {
    this.props.history.push(path);
  };
}

export default withRouter(PersonDetails);
