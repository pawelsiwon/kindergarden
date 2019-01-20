import React, { Component } from "react";
import Axios from "axios";
import { withRouter } from "react-router";
import Select from "react-select";

class ShowPayments extends Component {
  state = {
    payments: []
  };
  render() {
    return (
      <div className="container">
        <div className="row">
          <button
            className="btn btn-primary m-2"
            onClick={e => this.props.history.push("/app")}
          >
            Return
          </button>

          <table className="table table-striped table-dark table-hover table-sm">
            <thead className="thead-dark">
              <tr>
                <td className="align-middle">#</td>
                <td className="align-middle">Parent</td>
                <td className="align-middle">Child</td>
                <td className="align-middle">Name</td>
                <td className="align-middle">Choice</td>
                <td className="align-middle">Cost</td>
                <td className="align-middle" />
              </tr>
            </thead>
            <tbody>
              {this.state.payments.map(payment => (
                <tr key={payment.id}>
                  <th scope="row" className="align-middle">
                    {this.state.payments.indexOf(payment) + 1}
                  </th>
                  <td className="align-middle">
                    {payment.person.name + " " + payment.person.surname}
                  </td>
                  <td className="align-middle">
                    {payment.child.name + " " + payment.child.surname}
                  </td>
                  <td className="align-middle">{payment.name}</td>
                  <td className="align-middle">{payment.description}</td>
                  <td className="align-middle">{payment.cost}</td>
                  <td className="float-right align-middle" />
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }

  componentDidMount() {
    console.log(this.props.apiHost + "/payment/all", this.props.session);
    Axios.post(this.props.apiHost + "/payment/all", this.props.session)
      .then((res, req) => {
        console.log(res.data);
        

        this.setState({ payments: res.data });
      })
      .catch(err => console.log(err));
  }
}

export default withRouter(ShowPayments);
