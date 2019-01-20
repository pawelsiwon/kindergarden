import React, { Component } from "react";
import Axios from "axios";
import { withRouter } from "react-router";
import Select from "react-select";

class AddPayment extends Component {
  state = {
    childId: "",
    name: "Payment",
    paymentsIncludes: ["", ""],
    paymentId: "",

    persons: [],
    childs: [],
    child: {},
    payments: [],

    teachers: [],
    teacher: {},
    includes: []
  };
  render() {
    return (
      <div className="container">
        <form className="needs=validation">
          <div className="row">
            <button
              className="btn btn-primary m-2"
              onClick={e => this.props.history.push("/app")}
            >
              Return
            </button>
          </div>
          <h4>Add new payment</h4>
          <br />
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="firstName">Person ID</label>
              <Select
                options={this.state.teachers}
                onChange={e => this.setState({ teacher: e.value })}
              />
            </div>

            <div className="col-md-4 mb-3">
              <label htmlFor="firstName">Child ID</label>
              <Select
                options={this.state.childs}
                onChange={e => this.setState({ children: e.value })}
              />
            </div>

            <div className="col-md-4 mb-3">
              <label htmlFor="firstName">Name</label>
              <input
                id="firstName"
                className="form-control"
                type="text"
                value={this.state.name}
                onChange={e => this.setState({ name: e.target.value })}
              />
            </div>
            <div className="row">
              <div className="checkbox">
                <label>
                  <input type="checkbox" value="INSURANCE_FEE" />
                  Insurance fee
                </label>
                <div className="checkbox">
                  <label>
                    <input type="checkbox" value="MEALS_FEE" />
                    Meals fee
                  </label>
                </div>
                <div className="checkbox">
                  <label>
                    <input type="checkbox" value="SECOND_CHILD_BARGAIN" />
                    Second child bargain
                  </label>
                </div>
              </div>
            </div>
          </div>
          <div className="row">
            <button
              className="btn btn-danger col-md-1 m-2"
              onClick={this.resetForm}
            >
              Reset
            </button>
            <button
              className="btn btn-primary col-md-1 m-2"
              onClick={this.submitPayment}
            >
              Add
            </button>
          </div>
          <div
            className={"m-2 alert alert-" + this.state.alertType}
            role="alert"
          >
            {this.state.formMessage}
          </div>
        </form>
      </div>
    );
  }

  resetForm = () => {
    this.setState({
      name: "",
      surname: "",
      birthdate: "",

      pesel: "",
      phonenumber: "",
      role: "",

      email: "",
      login: "",
      password: "",

      city: "",
      postale: "",
      street: "",
      streetAddress: "",
      suite: "",

      lista: [
        { title: "Administrator", value: "admin" },
        { title: "Parent", value: "parent" },
        { title: "Teacher", value: "teacher" }
      ],
      formMessage: "",
      alertType: ""
    });
  };

  submitPayment = () => {
    console.log(this.props.apiHost + "/payment/add");
    const requestData = {
      loginData: this.props.session,
      childId: this.state.childId,
      name: "Payment",
      paymentsIncludes: ["", ""],
      paymentId: this.state.paymentId
    };
    console.log(requestData);
    Axios.post(this.props.apiHost + "/payment/add", requestData)
      .then(
        this.setState({
          alertType: "success",
          formMessage: "Person added!"
        })
      )
      .catch(
        this.setState({
          alertType: "warning",
          formMessage:
            "An error occured, please try again or contact with admin!"
        })
      );
  };

  componentDidMount() {
    console.log(
      this.props.apiHost + "/person/list/teachers",
      this.props.session
    );
    Axios.post(this.props.apiHost + "/person/list/teachers", this.props.session)
      .then((res, req) => {
        let obj1 = res.data.map(cl => {
          return { value: cl, label: cl.name + " " + cl.surname };
        });

        this.setState({ teachers: obj1 });

        console.log("teachers", this.state.teachers);
      })
      .catch(err => console.log(err));

    Axios.post(this.props.apiHost + "/child/list", this.props.session)
      .then(res => {
        let obj1 = res.data.map(cl => {
          return { value: cl, label: cl.name + " " + cl.surname };
        });

        this.setState({ childs: obj1 });
      })
      .catch(err => console.log(err));

    Axios.post(this.props.apiHost + "/payment/list", this.props.session)
      .then(res => {
        let obj1 = res.data.map(cl => {
          return { value: cl, label: cl.name + " " + cl.surname };
        });

        this.setState({ childs: obj1 });
      })
      .catch(err => console.log(err));

    this.setState({ yearStart: 2019, yearEnd: 2019 });
  }
}

export default withRouter(AddPayment);
